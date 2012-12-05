//
//  main.m
//  Meetup
//
//  Created by Tony Tam on 12/1/12.
//  Copyright (c) 2012 Tony Tam. All rights reserved.
//

#import "NIKMeetupApi.h"

#import <Foundation/Foundation.h>

int main(int argc, const char * argv[])
{

    @autoreleasepool {
        
        // insert code here...
        NSLog(@"Hello, World!");
        NIKMeetupApi * api = [[NIKMeetupApi alloc] init];
        
        bool done = false;
        static NSArray* meetups = nil;
        static NSError * gError = nil;
        
        [api findMeetupsWithCompletionBlock:@"hello" tag:nil active:nil completionHandler:^(NSArray * result, NSError * error) {
            if(error){
                gError = error;
            }
            else {
                meetups = [[NSArray alloc] arrayByAddingObjectsFromArray:result];
            }
        }];
        
        NSDate * loopUntil = [NSDate dateWithTimeIntervalSinceNow:10];
        while(!done && [loopUntil timeIntervalSinceNow] > 0){
            if(gError){
                NSLog(@"%@", gError);
                done = true;
            }
            if(meetups){
                NSLog(@"%@", meetups);
                done = true;
            }
        }

    }
    return 0;
}

