library "common"

node('slave-1')
{
	stage('hello')
	{
		print("hello world")
	}	
        stage('checkout')
	{
		common.checkoutrepo("git@github.com:codelessthinkmore/teleazure.git")
	}
}
