import requests

url = "http://localhost:8080/login"

payload = "{\n    \"email\": \"user@toy.com\",\n    \"password\": \"toy5555!@#$\"\n}"
headers = {'Content-Type': 'application/json; charset=utf-8'}

response = requests.request("POST", url, headers=headers, data=payload)

print(response.text)
