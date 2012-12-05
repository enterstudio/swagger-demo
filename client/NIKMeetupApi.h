#import <Foundation/Foundation.h>
#import "NIKApiInvoker.h"
#import "NIKMeetup.h"


@interface NIKMeetupApi: NSObject {

@private
    NSOperationQueue *_queue;
    NIKApiInvoker * _api;
}
@property(nonatomic, readonly) NSOperationQueue* queue;
@property(nonatomic, readonly) NIKApiInvoker* api;

-(void) addHeader:(NSString*)value forKey:(NSString*)key;

/**

 creates a meetup
 you need to be authenticated to do this!
 @param body the meetup to add
 */
-(void) addMeetupWithCompletionBlock :(NIKMeetup*) body 
        completionHandler: (void (^)(NSError* error))completionBlock;

/**

 searches meetups
 you will find great meetups here
 @param title title of meetup to search for
 @param tag tag of meetup to search for
 @param active searches for active meetups only
 */
-(void) findMeetupsWithCompletionBlock :(NSString*) title 
        tag:(NSString*) tag 
        active:(NSNumber*) active 
        completionHandler: (void (^)(NSArray* output, NSError* error))completionBlock;

@end
