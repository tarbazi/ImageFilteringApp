To run the code :
- image files to be used should be stored in the bin
- move to the bin directory and run the the command java <<program name>> <<input image name>> <<desired output image name>> <<window size>>
- example of the above is:- java MedianFilterParallel Sample.png SampleMedianParallel.jpg 5
- <<program name>> list is:- 1. MeanFilterSerial
                             2. MeanFilterParallel
                             3. MedianFilterSerial
                             4. MedianFilterParallel
- <<input image name>> is the input image name with an extension, e.g: Sample.png
- <<desired output image name>> is any desired image name with extension, e.g: Sample.png
- 

The program name and the dimensions are used as a mere example, they can be changed to the desired values

To generate java docs :
    make docs
    
