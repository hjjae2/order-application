import requests

# login
url = "http://localhost:8080/login"
payload = "{\n    \"email\": \"user@toy.com\",\n    \"password\": \"toy1234!@#$\"\n}"
headers = {'Content-Type': 'application/json; charset=utf-8'}
response = requests.request("POST", url, headers=headers, data=payload)

# order
url = "http://localhost:8080/orders"
payload = "{\n    \"orderItems\": [\n        {\n            \"productId\": 1,\n            \"quantity\": 1\n        }\n    ]\n}"
headers = {'Content-Type': 'application/json; charset=utf-8',"Cookie": response.headers['Set-Cookie']}
response = requests.request("POST", url, headers=headers, data=payload)
print(response.text)
