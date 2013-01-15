exports.models = {
  "Tag": {"id":"Tag","name":"","properties":{"name":{"type":"string","required":false}}},"Meetup": {"id":"Meetup","name":"","properties":{"title":{"type":"string","required":false},"tags":{"type":"Array","required":false,"items":{"$ref":"Tag"}},"active":{"type":"boolean","required":false}}}}
