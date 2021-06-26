library common

node('slave-1')
{
	stage('hello')
	{
		print("hello world")
	}	
        stage('progress')
	{
		common.firstfunction()
	}
}
