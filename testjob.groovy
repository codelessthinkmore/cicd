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
	        common.checkoutrepo("git@github.com:codelessthinkmore/cicd.git")
		common.checkoutrepo("git@github.com:codelessthinkmore/teleazure.git")
	}
}
