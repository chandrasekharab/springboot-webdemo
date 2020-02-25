# Introduction

Demo project to play around with SpringBoot, kotlin, docker, kubernetes and helm.

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


# Clean up for kubernetes

1. Removing kubernetes deployment

```shell script
$ kubectl delete deployment my-app
$ kubectl delete svc my-appdd
```

# Configure helm charts using local docker image

We going to configure docker image created locally "chan/springboot-webdemo".

1. Install the helms

```shell script
$ helm install example3 ./springbootwebdemo
```

2. Execute all commands listed in console

example:

```shell script
  $ export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/name=springbootwebdemo,app.kubernetes.io/instance=example3" -o jsonpath="{.items[0].metadata.name}")
  $ kubectl --namespace default port-forward $POD_NAME 8080:8080
```

Test the application by accessing below url

web: http://127.0.0.1:8080

rest api: http://127.0.0.1:8080/greeting?name=chan2

# Clean up helm installs

```shell script
$ helm uninstall example3
```

# Clean up for docker

1. Removing docker images

```shell script
$ docker rmi chan/springboot-webdemo --force
```