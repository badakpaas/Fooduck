import keras
from keras.models import load_model
from keras.preprocessing import image
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Flatten, MaxPooling2D,Dropout,Activation
from keras.preprocessing.image import ImageDataGenerator
import numpy as np

input_size = (128,128)
def isFood(image):
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
	 
	#Flatten
	model.add(Flatten())
	 
	#Full connection
	model.add(Dense(128, activation = 'relu'))
	model.add(Dense(2, activation = 'softmax'))
	 
	#Compile model
	model.compile(optimizer = 'adam', loss = 'categorical_crossentropy', metrics = ['accuracy'])

	model.load_weights('food_detection_weights.h5')


	prediction = model.predict_classes(images, batch_size=1)
	return(classes[prediction[0]])

img = image.load_img('test.jpg', target_size=input_size)
x = image.img_to_array(img)
x = np.expand_dims(x, axis=0)
images = np.vstack([x])

print(isFood(images))