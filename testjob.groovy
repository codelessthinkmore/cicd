library "common"

node('slave-1')
{
	stage('hello')
	{
		print("hello world")
	}	
        stage('checkout')
	{
		sh "whoami"
		common.checkoutrepo("teleazure")
	}
}
