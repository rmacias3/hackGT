from flask import Flask
from flask import request
import flask
import filter

app = Flask(__name__)

@app.route('/')
def index():
	year = request.args.get("year")
	month = request.args.get("month")
	zip_code = request.args.get("zip_code")
	print year,month,zip_code
	print "ghghhhhhhhhhhhhhhhhh"
	years = [year]
	months = [month]
	zips = [zip_code]
	categories = ["1","1","1","1"]
	totals = [0,0,0,0,0,0,0,0]
	lat = ["33.774151","33.7739563","33.7759899","33.7459899","33.7469899","33.7569899","33.7685748","33.7635748"]
	lon = ["-84.396424","-84.3878713","-84.3863809","-84.3963809","-84.3933809","-84.3833809","-84.3931313","-84.3961313"]

	f = open("data_created.csv","r")
	f.readline()
	for line in f.readlines():
		# print line
		line = line.strip()
		tokens = line.split(",")
		if tokens[4] in zips:
			if tokens[6] in months:
				if tokens[7] in years:
					# if tokens[0] is categories[0]
					# 	if tokens[1] is categories[1]:
					# 		if tokens[2] is categories[2]:
					# 			if tokens[3] is categories[3]:
					totals[int(tokens[5])-1] += int(tokens[8])

	total = 0
	for i in range(8):
		total += totals[i]

	# fw = open("graph_data.csv","w")
	return_val = []
	for i in range(8):
		return_val.append({"index" : str(i+1), "latitude": str(lat[i]), "longitude": str(lon[i]), "totals": str((totals[i]*8.0)/total)})
	# fw.close()
	return flask.jsonify(**{"data":return_val})

if __name__ == '__main__':
	app.run(debug=True)