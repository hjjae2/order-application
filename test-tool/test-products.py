import requests

url = "http://localhost:8080/products"

payload={}
headers = {'Content-Type': 'application/json; charset=utf-8'}

response = requests.request("GET", url, headers=headers, data=payload)

print(response.text)
