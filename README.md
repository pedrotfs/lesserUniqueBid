# Lesser Unique Bids

### Technology

Built with Java 8, spring boot, rest endpoints, Junit 5, Docker
Docker Compose, Google's Gson and Postman

###About

This solution receive bids in Json format on the endpoint /auction/bid. Use postman
or an app with the same function and provide the bids by post. There are samples
in the resource files that can be used and built upon. there is also the class
SampleGenerator that generate console logs that can be repurposed to create large
bid samples with simple a minor tweak in its parameters. the bid json object must be
named bids by default, but on the properties files it can be configured there.

other configurations include the how many bids will be computed, how much
each bid cost and how many decimal places are valid(if the decimal places 
exceed this configuration, the program will throw an exception)

To get the auction winner, use the get endpoint /auction/lowest.
it will return the winner in a json format. 

The postman collection is provided in the same folder as the samples

###How to use

Call to /auction/bid with the bids in request body (use the samples for reference)
Then call /auction/lowest to get the result

###Configurations in properties files

bids.identifier = bids \
bids.limit = 999 \
bids.price = 0.98 \
bids.decimalPlaces = 2

### Docker images
pedrotfs/lesseruniquebid:1.0.0