package easyFlyer.file;

public class FileFormatNotSupportedException 
	extends Exception 
{
	private static final long serialVersionUID = -7413732337319838093L;

	private String format;
	
	public FileFormatNotSupportedException(String format)
	{
		this.format = format;
	}
	
	public String getMessage()
	{
		return "FileFormat not supported: " + format;
	}
}
