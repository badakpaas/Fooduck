import keras
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Flatten, MaxPooling2D,Dropout,Activation
from keras.preprocessing.image import ImageDataGenerator

input_size = (128,128)
#loading data
train_datagen = ImageDataGenerator(rescale=1./255, shear_range=0.2, zoom_range=0.2, horizontal_flip=True)
test_datagen = ImageDataGenerator(rescale=1./255)

train_data = train_datagen.flow_from_directory('../datasets/noodles_classification/train',target_size=input_size,classes=['pho','ramen'],batch_size = 10)
validation_data = test_datagen.flow_from_directory('../datasets/noodles_classification/validation',target_size=input_size,classes=['pho','ramen'],batch_size = 10)

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


#fit model
model.fit_generator(train_data,steps_per_epoch=180,validation_data=validation_data,validation_steps=20,epochs = 20 ,verbose = 1)


#save mode
model.save_weights('noodles_classification_weights.h5')
