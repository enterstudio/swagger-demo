#import <Foundation/Foundation.h>
#import "NIKSwaggerObject.h"
#import "NIKTag.h"

@interface NIKMeetup : NIKSwaggerObject

@property(nonatomic) NSString* title;
@property(nonatomic) NSArray* tags;
@property(nonatomic) NSNumber* active;
- (id) title: (NSString*) title
     tags: (NSArray*) tags
     active: (NSNumber*) active;

- (id) initWithValues: (NSDictionary*)dict;
- (NSDictionary*) asDictionary;


@end

