# Texel Interview

Java app to run ffmpeg and extract and analyse freeze frame info from video clips for comparison.  

# System Requirements
java 16.0.1  
maven (latest)  
ide - preferably IntelliJ  
ffmpeg - v4 or higher.    
note: Make sure ffmpeg is in your path.  If not, the FQN can be configured in the 'EntryPoint.java' file.

# Configuration

Configuration currently is done in the 'EntryPoint.java' file.  
There are some constants at the top of the file.  
To select the which of the sample video clips are downloaded and compared, comment/uncomment the URLs in the 'videoUrls' list.

# Run
In your ide, run the main function in EntryPoint.java.

