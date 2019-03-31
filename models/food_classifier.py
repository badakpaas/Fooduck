import keras
from keras.models import load_model
from keras.preprocessing import image
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Flatten, MaxPooling2D,Dropout,Activation
from keras.preprocessing.image import ImageDataGenerator
import numpy as np
from keras import backend as k

def foodItem(images):
	input_size = (128,128)
	classes = {0:'apple_pie',1:'baklava',2:'bibimbap',3:'bread_pudding',4:'chesecake',5:'chicken_wings',6:'churros',7:'donuts',8:'dumplings',9:'falafel',10:'fish_and_chips',11:'hamburger',12:'ice_cream',13:'noodles',14:'pancakes',15:'pizza'}

	#design model
	model = Sequential()

	#Convolution and Max pooling
	model.add(Conv2D(32, (3, 3), input_shape = (128, 128, 3), activation = 'relu'))
	model.add(MaxPooling2D(pool_size = (2,2)))
	model.add(Conv2D(64, (3, 3), activation = 'relu'))
	model.add(MaxPooling2D(pool_size = (2,2)))
	model.add(Conv2D(128, (3, 3), activation = 'relu'))
	model.add(MaxPooling2D(pool_size = (2,2)))
	model.add(Conv2D(256, (3, 3), activation = 'relu'))
	model.add(MaxPooling2D(pool_size = (2,2)))
	model.add(Conv2D(512, (3, 3), activation = 'relu'))
	model.add(MaxPooling2D(pool_size = (2,2)))
	 
	#Flatten
	model.add(Flatten())
	 
	#Full connection
	model.add(Dense(512, activation = 'relu'))
	model.add(Dense(256, activation = 'relu'))
	model.add(Dense(16, activation = 'softmax'))
	 
	#Compile model
	model.compile(optimizer = 'adam', loss = 'categorical_crossentropy', metrics = ['accuracy'])

	model.load_weights('./models/food_classification_weights.h5')


	prediction = model.predict_classes(images, batch_size=1)
	k.clear_session()
	return(classes[prediction[0]])
