from flask import Flask
import json

app = Flask(__name__)

@app.route('/',methods = ['GET'])
def home():
	#request.data
	print("request")
	data = '{"status":"success"}'
	return data


@app.route('/signin',methods = ['POST'])
def signup():
	print(request.data)
	data = '{"status":"success"}'
	return json.loads(data)

@app.route('/login',methods = ['POST'])
def login():
	print(request.data)
	data = '{"status":"success"}'
	return json.loads(data)

@app.route('/sendImage',methods = ['POST'])
def handleImage():
	print(request.form)
	data = '{"status":"success"}'
	return json.loads(data)

@app.route('/getDiet',methods = ['GET'])
def getDiet():
	print(request.data)
	data = '{"status":"success"}'
	return json.loads(data)

@app.route('/getWorkout',methods = ['GET'])
def getWorkout():
	print(request.data)
	data = '{"status":"success"}'
	return json.loads(data)

@app.route('/getStats',methods = ['GET'])
def getStats():
	print(request.data)
	data = '{"status":"success"}'
	return json.loads(data)


#Run server on local IP and port 8080
if __name__ == '__main__':
   app.run('0.0.0.0',8080)