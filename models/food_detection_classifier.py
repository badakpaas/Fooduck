import keras
from keras.models import load_model
from keras.preprocessing import image
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Flatten, MaxPooling2D,Dropout,Activation
from keras.preprocessing.image import ImageDataGenerator
import numpy as np
from keras import backend as k

def isFood(images):
	input_size = (128,128)
	classes = {0:'food',1:'others'}

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
	model.add(Dense(2, activation = 'softmax'))
	 
	#Compile model
	model.compile(optimizer = 'adam', loss = 'categorical_crossentropy', metrics = ['accuracy'])

	model.load_weights('./models/food_detection_weights.h5')


	prediction = model.predict_classes(images, batch_size=1)
	k.clear_session()
	return(classes[prediction[0]])
