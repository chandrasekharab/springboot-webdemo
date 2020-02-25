# Introduction

Demo project to play around with Spring boot, kotlin, docker and kubernetes.

For this I am using mac with desktop version of docker and make sure to enable kubernetes
in docker preferences.

# Build and Install docker

From the project folder

1. Create docker image
```shell script
$ docker build -t chan/springboot-webdemo .
```

2.  Run docker container
```shell script
$ docker run -p 8080:8080 -t chan/springboot-webdemo
```

Test the application by accessing below url
web: http://localhost:8080
rest api: http://localhost:8080/greeting?name=chan2

3. Remove docker container 

    Get container id by running below command
    
    ```shell script
    $ docker container ps -a
    ```
    
    Once you are confident of working of docker then feel free to remove
     the container so that we can try configuring kubernetes.
    ```shell script
    $ docker container rm <container_id>
    ```
    
# Configuring Kubernetes

Get the docker image name by running below command
```shell script
$ docker images
```
Note down the image and tag.
eq., chan/springboot-webdemo and latest

1. Run below command to create pods

    ```shell script
    $ kubectl run chanapp --image=chan/springboot-webdemo:latest --port=3000 --image-pull-policy=IfNotPresent
    ```
    
    By running below command, you can see all running pods
    ```shell script
    $ kubectl get pods
    ```

2. Expose app to loadbalancer so that we can access services

    ```shell script
    $ kubectl expose deployment chanapp --type=LoadBalancer --port=8080 --target-port=8080
    ```
    
    Test the application by accessing below url
    
    web: http://localhost:8080
    
    rest api: http://localhost:8080/greeting?name=chan2


# Clean up

1. Removing docker images

```shell script
$ docker rmi chan/springboot-webdemo --force
```

2. Removing kubernetes deployment

```shell script
$ kubectl delete deployment my-app
$ kubectl delete svc my-appdd
```
