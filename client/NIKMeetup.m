#import "NIKDate.h"
#import "NIKMeetup.h"

@implementation NIKMeetup

-(id)title: (NSString*) title
    tags: (NSArray*) tags
{
  _title = title;
  _tags = tags;
  return self;
}

-(id) initWithValues:(NSDictionary*)dict
{
    self = [super init];
    if(self) {
        _title = dict[@"title"]; 
        id tags_dict = dict[@"tags"];
        if([tags_dict isKindOfClass:[NSArray class]]) {

            NSMutableArray * objs = [[NSMutableArray alloc] initWithCapacity:[(NSArray*)tags_dict count]];

            if([(NSArray*)tags_dict count] > 0) {
                for (NSDictionary* dict in (NSArray*)tags_dict) {
                    NIKTag* d = [[NIKTag alloc] initWithValues:dict];
                    [objs addObject:d];
                }
                
                _tags = [[NSArray alloc] initWithArray:objs];
            }
            else {
                _tags = [[NSArray alloc] init];
            }
        }
        else {
            _tags = [[NSArray alloc] init];
        }
        

    }
    return self;
}

-(NSDictionary*) asDictionary {
    NSMutableDictionary* dict = [[NSMutableDictionary alloc] init];
    if(_title != nil) dict[@"title"] = _title ;
    if(_tags != nil){
        if([_tags isKindOfClass:[NSArray class]]){
            NSMutableArray * array = [[NSMutableArray alloc] init];
            for( NIKTag *tags in (NSArray*)_tags) {
                [array addObject:[(NIKSwaggerObject*)tags asDictionary]];
            }
            dict[@"tags"] = array;
        }
        else if(_tags && [_tags isKindOfClass:[NIKDate class]]) {
            NSString * dateString = [(NIKDate*)_tags toString];
            if(dateString){
                dict[@"tags"] = dateString;
            }
        }
    }
    else {
    if(_tags != nil) dict[@"tags"] = [(NIKSwaggerObject*)_tags asDictionary];
    }
    NSDictionary* output = [dict copy];
    return output;
}

@end

