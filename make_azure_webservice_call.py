import urllib2
# If you are using Python 3+, import urllib instead of urllib2

import json 



data =  {

        "Inputs": {

                "input1":
                {
                    "ColumnNames": ["casuals", "jackets", "shoes", "formals", "zip_code", "month", "sales"],
                    "Values": [ [ 1, 1, 0,1,"30318", 4, "10" ], [ 1, 1, 0,1,"30318", 4, "10" ], ]
                },        },
            "GlobalParameters": {
}
    }


body = str.encode(json.dumps(data))

url = 'https://ussouthcentral.services.azureml.net/workspaces/7f29d25c24934d8d93a67b615ad2714a/services/28f9eb3825f448eaa9bfe10a4ffbf888/execute?api-version=2.0&details=true'
# api_key = 'T0WtfiEngJLEZWiw55xTkw+JzxT+u8EriXmj1VC3/TfXBXI49JxhQ/kl2e2+Mrow0zjmsmI7IcJC6SFX1rCZtw==' # Replace this with the API key for the web service
# api_key= 'nM31V5EgVJ7j2GAqDmTzNlbjlYiqkb8/kZLqjwsL6H6LzSmJqbbdXNel9SxR8oGkhYnb2ENMNj5Iuy1KZrIJWQ=='
api_key = 'nM31V5EgVJ7j2GAqDmTzNlbjlYiqkb8/kZLqjwsL6H6LzSmJqbbdXNel9SxR8oGkhYnb2ENMNj5Iuy1KZrIJWQ=='
headers = {'Content-Type':'application/json', 'Authorization':('Bearer '+ api_key)}

req = urllib2.Request(url, body, headers) 

try:
    response = urllib2.urlopen(req)

    # If you are using Python 3+, replace urllib2 with urllib.request in the above code:
    # req = urllib.request.Request(url, body, headers) 
    # response = urllib.request.urlopen(req)

    result = response.read()
    print(result) 
except urllib2.HTTPError, error:
    print("The request failed with status code: " + str(error.code))

    # Print the headers - they include the requert ID and the timestamp, which are useful for debugging the failure
    print(error.info())

    print(json.loads(error.read())) 
