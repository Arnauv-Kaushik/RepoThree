import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3upload1 
{
	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{
	
	//AWSCredentials credentials = new BasicAWSCredentials("AKIAZ2AMB5EXTWRNKNNR","XaKWjjmNHmIRqyimdDq4F3GXxSDa6HgjMZT0P9YJ");//Root creds
	//AWSCredentials credentials = new BasicAWSCredentials("AKIAZ2AMB5EXZDVJKWY2","3tQlJlcLFku9dilO+y7yIMyCITfIqdjMgfvgawu+");//ASKAdminUser1
		AWSCredentials credentials = new BasicAWSCredentials("AKIAZ2AMB5EXTWRNKNNR","XaKWjjmNHmIRqyimdDq4F3GXxSDa6HgjMZT0P9YJ");//ASKawsAdmin
	System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true");
	AmazonS3 s3client = null;	
	String bucketName = "awsjenkins1";
	String myRegion = "us-east-1";
	Regions regioName = Regions.fromName(myRegion);
	Regions regions = regioName;
		
	 s3client = AmazonS3ClientBuilder
			  .standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(regions)
			  .build();
	
		/*
		 * if(s3client.doesBucketExist(bucketName)) {
		 * System.out.println("Bucket name is not available." +
		 * " Try again with a different Bucket name."); return; }
		 */
	 
		/*
		 * s3client.createBucket(bucketName); System.out.println("created bucket ::::" +
		 * bucketName);
		 */
	

		/*
		 * List<Bucket> buckets = s3client.listBuckets();
		 * System.out.println("Listing bucket names");
		 * 
		 * for(Bucket bucket : buckets) { System.out.println(bucket.getName()); }
		 */
	
		String key_name = "KeyName1";//direct folder name new folder in the bucket itself, else path+/ new_folder_name
		//for delete, deletes keynames INCLUDING the entered keyname, f99/ will delete all contents of f99 folder INCLUDING the folder f99 itself
		String folder_name = "Folder1";
		
		  String file_path = "E://ASK/AWS/SEScode.txt"; File file = new File(file_path);
		 try
		 {
			 PutObjectRequest req = new PutObjectRequest(bucketName ,key_name,file); 
		
			// DeleteObjectRequest dreq = new DeleteObjectRequest(bucketName, key_name);
		s3client.putObject(req);
			 System.out.println("UPLOADING........... !!");
			 System.out.println("UPLOADED SUCCESSFULLY!!");
			 
			
			/*
			 * System.out.println("TRYING TO DELETE ........... !!");
			 * 
			 * for(S3ObjectSummary s3osfile :
			 * s3client.listObjects(bucketName,key_name).getObjectSummaries()) {
			 * s3client.deleteObject(bucketName,s3osfile.getKey()); }
			 */
			 
			 
			 Object metadata = new ObjectMetadata();
			 ((ObjectMetadata) metadata).setContentType("binary/octet-stream");
			 InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
			 s3client.putObject(bucketName, key_name, new ByteArrayInputStream(new byte[0]), (ObjectMetadata) metadata);
			 PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key_name, emptyContent, (ObjectMetadata) metadata);
			 s3client.putObject(putObjectRequest);
		 }
		 catch(Exception e)
		 {
			 System.out.println("Caught Exception ::::" + e);
		 }
	
		System.out.println("Listing contents of bucket ::::" + bucketName);

		 ObjectListing objectListing = s3client.listObjects(bucketName);
		  for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
		  System.out.println(os.getKey()); }
		
	}
	
	
}
