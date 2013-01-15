#import "NIKMeetupApi.h"
#import "NIKMeetup.h"



@implementation NIKMeetupApi
static NSString * basePath = @"http://localhost:8000/api";

@synthesize queue = _queue;
@synthesize api = _api;

+(NIKMeetupApi*) apiWithHeader:(NSString*)headerValue key:(NSString*)key {
    static NIKMeetupApi* singletonAPI = nil;

    if (singletonAPI == nil) {
        singletonAPI = [[NIKMeetupApi alloc] init];
        [singletonAPI addHeader:headerValue forKey:key];
    }
    return singletonAPI;
}

-(id) init {
    self = [super init];
    _queue = [[NSOperationQueue alloc] init];
    _api = [NIKApiInvoker sharedInstance];

    return self;
}

-(void) addHeader:(NSString*) value
           forKey:(NSString*)key {
    [_api addHeader:value forKey:key];
}

-(void) addMeetupWithCompletionBlock:(NIKMeetup*) body
        completionHandler: (void (^)(NSError* error))completionBlock{

    NSMutableString* requestUrl = [NSMutableString stringWithFormat:@"%@/meetup.{format}", basePath];

    // remove format in URL if needed
    if ([requestUrl rangeOfString:@".{format}"].location != NSNotFound)
        [requestUrl replaceCharactersInRange: [requestUrl rangeOfString:@".{format}"] withString:@".json"];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary* headerParams = [[NSMutableDictionary alloc] init];
    id bodyDictionary = nil;
        if(body != nil && [body isKindOfClass:[NSArray class]]){
        NSMutableArray * objs = [[NSMutableArray alloc] init];
        for (id dict in (NSArray*)body) {
            if([dict respondsToSelector:@selector(asDictionary)]) {
                [objs addObject:[(NIKSwaggerObject*)dict asDictionary]];
            }
            else{
                [objs addObject:dict];
            }
        }
        bodyDictionary = objs;
    }
    else if([body respondsToSelector:@selector(asDictionary)]) {
        bodyDictionary = [(NIKSwaggerObject*)body asDictionary];
    }
    else if([body isKindOfClass:[NSString class]]) {
        bodyDictionary = body;
    }
    else{
        NSLog(@"don't know what to do with %@", body);
    }

    if(body == nil) {
        // error
    }
    [_api stringWithCompletionBlock:requestUrl 
                             method:@"POST" 
                        queryParams:queryParams 
                               body:bodyDictionary 
                       headerParams:headerParams
                    completionBlock:^(NSString *data, NSError *error) {
        if (error) {
            completionBlock(error);
            return;
        }
        completionBlock(nil);
    }];
    

}

-(void) findMeetupsWithCompletionBlock:(NSString*) title
        tag:(NSString*) tag
        active:(NSNumber*) active
        completionHandler: (void (^)(NSArray* output, NSError* error))completionBlock{

    NSMutableString* requestUrl = [NSMutableString stringWithFormat:@"%@/meetup.{format}", basePath];

    // remove format in URL if needed
    if ([requestUrl rangeOfString:@".{format}"].location != NSNotFound)
        [requestUrl replaceCharactersInRange: [requestUrl rangeOfString:@".{format}"] withString:@".json"];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if(title != nil)
        queryParams[@"title"] = title;
    if(tag != nil)
        queryParams[@"tag"] = tag;
    if(active != nil)
        queryParams[@"active"] = active;
    NSMutableDictionary* headerParams = [[NSMutableDictionary alloc] init];
    id bodyDictionary = nil;
        [_api dictionary: requestUrl 
              method: @"GET" 
         queryParams: queryParams 
                body: bodyDictionary 
        headerParams: headerParams
     completionBlock: ^(NSDictionary *data, NSError *error) {
        if (error) {
            completionBlock(nil, error);return;
        }
        
        if([data isKindOfClass:[NSArray class]]){
            NSMutableArray * objs = [[NSMutableArray alloc] initWithCapacity:[data count]];
            for (NSDictionary* dict in (NSArray*)data) {
                NIKMeetup* d = [[NIKMeetup alloc]initWithValues: dict];
                [objs addObject:d];
            }
            completionBlock(objs, nil);
        }
        }];
    

}

-(void) addMeetupAsJsonWithCompletionBlock :(NIKMeetup*) body 

        completionHandler:(void (^)(NSError *))completionBlock{

    NSMutableString* requestUrl = [NSMutableString stringWithFormat:@"%@/meetup.{format}", basePath];

    // remove format in URL if needed
    if ([requestUrl rangeOfString:@".{format}"].location != NSNotFound)
        [requestUrl replaceCharactersInRange: [requestUrl rangeOfString:@".{format}"] withString:@""];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    NSMutableDictionary* headerParams = [[NSMutableDictionary alloc] init];
    id bodyDictionary = nil;
    if(body != nil && [body isKindOfClass:[NSArray class]]){
        NSMutableArray * objs = [[NSMutableArray alloc] init];
        for (id dict in (NSArray*)body) {
            if([dict respondsToSelector:@selector(asDictionary)]) {
                [objs addObject:[(NIKSwaggerObject*)dict asDictionary]];
            }
            else{
                [objs addObject:dict];
            }
        }
        bodyDictionary = objs;
    }
    else if([body respondsToSelector:@selector(asDictionary)]) {
        bodyDictionary = [(NIKSwaggerObject*)body asDictionary];
    }
    else if([body isKindOfClass:[NSString class]]) {
        bodyDictionary = body;
    }
    else{
        NSLog(@"don't know what to do with %@", body);
    }

    if(body == nil) {
        // error
    }
    [_api dictionary:requestUrl 
              method:@"POST" 
         queryParams:queryParams 
                body:bodyDictionary 
        headerParams:headerParams
     completionBlock:^(NSDictionary *data, NSError *error) {
        if (error) {
            completionBlock(error);return;
        }

        completionBlock(nil);

    }];


}

-(void) findMeetupsAsJsonWithCompletionBlock :(NSString*) title 
tag:(NSString*) tag 
active:(NSNumber*) active 

        completionHandler:(void (^)(NSString*, NSError *))completionBlock{

    NSMutableString* requestUrl = [NSMutableString stringWithFormat:@"%@/meetup.{format}", basePath];

    // remove format in URL if needed
    if ([requestUrl rangeOfString:@".{format}"].location != NSNotFound)
        [requestUrl replaceCharactersInRange: [requestUrl rangeOfString:@".{format}"] withString:@""];

    NSMutableDictionary* queryParams = [[NSMutableDictionary alloc] init];
    if(title != nil)
        queryParams[@"title"] = title;
    if(tag != nil)
        queryParams[@"tag"] = tag;
    if(active != nil)
        queryParams[@"active"] = active;
    NSMutableDictionary* headerParams = [[NSMutableDictionary alloc] init];
    id bodyDictionary = nil;
    [_api dictionary:requestUrl 
              method:@"GET" 
         queryParams:queryParams 
                body:bodyDictionary 
        headerParams:headerParams
     completionBlock:^(NSDictionary *data, NSError *error) {
        if (error) {
            completionBlock(nil, error);return;
        }

        NSData * responseData = nil;
            if([data isKindOfClass:[NSDictionary class]]){
                responseData = [NSJSONSerialization dataWithJSONObject:data
                                                               options:kNilOptions error:&error];
            }
            else if ([data isKindOfClass:[NSArray class]]){
                responseData = [NSJSONSerialization dataWithJSONObject:data
                                                               options:kNilOptions error:&error];
            }
            NSString * json = [[NSString alloc]initWithData:responseData encoding:NSUTF8StringEncoding];
            completionBlock(json, nil);
        

    }];


}


@end
