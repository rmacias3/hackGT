import requests
# -*- coding: utf-8 -*-
"""
Yelp API v2.0 code sample.
This program demonstrates the capability of the Yelp API version 2.0
by using the Search API to query for businesses by a search term and location,
and the Business API to query additional information about the top result
from the search query.
Please refer to http://www.yelp.com/developers/documentation for the API documentation.
This program requires the Python oauth2 library, which you can install via:
`pip install -r requirements.txt`.
Sample usage of the program:
`python sample.py --term="bars" --location="San Francisco, CA"`
"""
import argparse
import json
import pprint
import sys
import urllib
import urllib2
import oauth2
import csv

API_HOST = 'api.yelp.com'
DEFAULT_TERM = 'Fashion'
DEFAULT_LOCATION = 'Atlanta, GA'
SEARCH_LIMIT = 50
SEARCH_PATH = '/v2/search/'
BUSINESS_PATH = '/v2/business/'

# OAuth credential placeholders that must be filled in by users.
CONSUMER_KEY = 'scqL9UcdK_phtYYn_5Zkcg'
CONSUMER_SECRET = 'vS7_WJsnb65ul13kGnQKepqOTig'
TOKEN = 'GSbzTCrVG8oGSG0efiW8N6yd4D2ynYF_'
TOKEN_SECRET = 'Aq0x4SYz7kxM8PfckYoJp4PWvNs'


def request(host, path, url_params=None):
    """Prepares OAuth authentication and sends the request to the API.
    Args:
        host (str): The domain host of the API.
        path (str): The path of the API after the domain.
        url_params (dict): An optional set of query parameters in the request.
    Returns:
        dict: The JSON response from the request.
    Raises:
        urllib2.HTTPError: An error occurs from the HTTP request.
    """
    url_params = url_params or {}
    url = 'http://{0}{1}?'.format(host, urllib.quote(path.encode('utf8')))

    consumer = oauth2.Consumer(CONSUMER_KEY, CONSUMER_SECRET)
    oauth_request = oauth2.Request(method="GET", url=url, parameters=url_params)

    oauth_request.update(
        {
            'oauth_nonce': oauth2.generate_nonce(),
            'oauth_timestamp': oauth2.generate_timestamp(),
            'oauth_token': TOKEN,
            'oauth_consumer_key': CONSUMER_KEY
        }
    )
    token = oauth2.Token(TOKEN, TOKEN_SECRET)
    oauth_request.sign_request(oauth2.SignatureMethod_HMAC_SHA1(), consumer, token)
    signed_url = oauth_request.to_url()

    print u'Querying {0} ...'.format(url)

    conn = urllib2.urlopen(signed_url, None)
    try:
        response = json.loads(conn.read())
    finally:
        conn.close()

    return response

def search(term, location):
    """Query the Search API by a search term and location.
    Args:
        term (str): The search term passed to the API.
        location (str): The search location passed to the API.
    Returns:
        dict: The JSON response from the request.
    """

    url_params = {
        'term': term.replace(' ', '+'),
        'location': location.replace(' ', '+'),
        'limit': SEARCH_LIMIT
    }
    return request(API_HOST, SEARCH_PATH, url_params=url_params)

def get_business(business_id):
    """Query the Business API by a business ID.
    Args:
        business_id (str): The ID of the business to query.
    Returns:
        dict: The JSON response from the request.
    """
    business_path = BUSINESS_PATH + business_id

    return request(API_HOST, business_path)

def query_api(term, location):
    """Queries the API by the input values from the user.
    Args:
        term (str): The search term to query.
        location (str): The location of the business to query.
    """
    response = search(term, location)

    businesses = response.get('businesses')

    if not businesses:
        print u'No businesses for {0} in {1} found.'.format(term, location)
        return

    business_id = businesses[0]['id']

    #print u'{0} businesses found, querying business info for the top result "{1}" ...'.format(
       # len(businesses),
        #business_id
    #)

    response = get_business(business_id)

    #print u'Result for business "{0}" found:'.format(business_id)
    #pprint.pprint(response, indent=2)
    return businesses

def sendToCapitalOne(name,categories,street_name,state_name,city_name,postal_code, houseNumber,longitude,latitude):
    baseURL="http://api.reimaginebanking.com/"
    key= "?key=a990c6605c6d16f21df0ac6928050ee3"
    merchantURL=baseURL +"merchants"+ key
    body={
        "name": name,
        "category": categories,
        "address": {
            "street_number": houseNumber,
            "street_name": street_name,
            "city": city_name,
            "state": state_name,
            "zip": postal_code
        },
        "geocode": {
        "lat": latitude,
        "lng": longitude
        }
        }
    r=requests.post(merchantURL,json=body)
    print r.text
    


def main():
    parser = argparse.ArgumentParser()

    parser.add_argument('-q', '--term', dest='term', default=DEFAULT_TERM, type=str, help='Search term (default: %(default)s)')
    parser.add_argument('-l', '--location', dest='location', default=DEFAULT_LOCATION, type=str, help='Search location (default: %(default)s)')

    input_values = parser.parse_args()
    try:
       parsed_json = query_api(input_values.term, input_values.location)
       #print (parsed_json)
       
       #gets the category for us
       #for i in range(4):
        #print(parsed_json[0]["categories"][0][0])
        #print(parsed_json[0]["name"])
        #print(parsed_json[0][""])
        
       for business in parsed_json:
          name=business["id"]
          #print("Name:"+business["id"])
          categories=business["categories"][0][0]
          #print("Categories:"+business["categories"][0][0])
          street_name=business["location"]["neighborhoods"][0]
          #print("Street Name:"+business["location"]["neighborhoods"][0])
          state_name=business["location"]["state_code"]
          #print("State Name:"+business["location"]["state_code"])
          city_name=business["location"]["city"]
          #print("City Name:"+business["location"]["city"])
          longitude=str(business["location"]["coordinate"]["longitude"])
          #print("Longitude:"+str(business["location"]["coordinate"]["longitude"]))
          zip_code=str(business["location"]["postal_code"])
          #print("Postal Code:"+str(business["location"]["postal_code"]))
          latitude=str(business["location"]["coordinate"]["latitude"])
          #print("Latitude:"+str(business["location"]["coordinate"]["latitude"]))
          houseNumber=business["location"]["display_address"][0][0]
          #print("House Number: "+ business["location"]["display_address"][0])
          sendToCapitalOne(name,categories,street_name,state_name,city_name, zip_code, houseNumber, longitude,latitude)

       #sendToCapitalOne()
       #with open('Data.csv', 'wb') as csvfile:
        #    spamwriter = csv.writer(csvfile, delimiter=' ',
         #                   quotechar='|', quoting=csv.QUOTE_MINIMAL)
          #  for business in parsed_json:
           #     spamwriter.writerow([business['id'], business['categories'], business['location']['address'], business['location']['coordinate']['latitude'], business['location']['coordinate']['longitude'], business['rating'], business['review_count']])
    

    except urllib2.HTTPError as error:
        sys.exit('Encountered HTTP error {0}. Abort program.'.format(error.code))

if __name__ == '__main__':
    main()
