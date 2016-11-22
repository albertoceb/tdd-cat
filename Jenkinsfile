node ('master'){
  stage 'Build and Test'
  checkout scm
  sh 'gradle test'
  stage 'Build and Test again'
  checkout scm
  sh 'gradle test'
 
 }
