def checkoutrepo(reponame,branchname)
{
    git branch: ${branchname},
    credentialsId: '06116240-c970-4653-abf1-271ada2586c3',
	    url: "git@github.com:codelessthinkmore/${reponame}.git"
}

def BuildDockerImage(reponame,imagename,regurl)
{
    sh """
    
    cd ${reponame}
    
    ls -l

    docker build  --tag "$regurl/$imagename:v$BUILD_NUMBER" .
    
    docker push "$regurl/$imagename:v$BUILD_NUMBER"

    export GoDockerBuildTag="$BUILD_NUMBER"

    """

}

def getLastSuccessfulBuildNumber(String jobPath){
	/*
	params:
		jobPath: type String :: path of job including folder name.
						 Ex:  DAaaS-k8s-CI-mergereq2master/CI-INTEGRATION_TEST
	*/
	
	def lastSuccessfulBuildNumber = ""
	try{
		if (jobPath.contains('/')){
			def job = Jenkins.getInstance().getItemByFullName(jobPath, Job.class)
			lastSuccessfulBuildNumber = job.lastSuccessfulBuild.displayName
		}else{
			lastSuccessfulBuildNumber = Jenkins.instance.getItem(jobPath).lastSuccessfulBuild.displayName
		}
		
	}catch(Exception){
		println(String.format("job: %s is invalid", jobPath))
		println("exception: "+Exception)
	}
	println(String.format("Last Successful Build number of job: %s  is %s", jobPath, lastSuccessfulBuildNumber))
	return lastSuccessfulBuildNumber.replace("#", "")
}
