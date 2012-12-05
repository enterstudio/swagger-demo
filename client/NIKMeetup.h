#import <Foundation/Foundation.h>
#import "NIKSwaggerObject.h"
#import "NIKTag.h"

@interface NIKMeetup : NIKSwaggerObject

@property(nonatomic) NSString* title;
@property(nonatomic) NSArray* tags;
- (id) title: (NSString*) title
     tags: (NSArray*) tags;

- (id) initWithValues: (NSDictionary*)dict;
- (NSDictionary*) asDictionary;


@end

