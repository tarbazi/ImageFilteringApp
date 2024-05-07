To run the code :
- image files to be used should be stored in the bin
- move to the bin directory and run the the command java <<program_name>> <<input_image_name>> <<desired_output_image_name>> <<window_size>>
- example of the above is:- java MedianFilterParallel Sample.png SampleMedianParallel.jpg 5
- <<program_name>> list is:- 1. MeanFilterSerial
                             2. MeanFilterParallel
                             3. MedianFilterSerial
                             4. MedianFilterParallel
- <<input_image_name>> is the input image name with an extension, e.g: Sample.png
- <<desired_output_image_name>> is any desired image name with extension, e.g: Sample.png
- <<window_size>> is an odd values greater than and not equal to 1 but less than the dimensions of the input image. i.e it it adviseable to keep this value below 23

The program name and the dimensions are used as a mere example, they can be changed to the desired values

To generate java docs :
    make docs
    
