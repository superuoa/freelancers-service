1.Request a Remote OpenShift Lab Environment

2.Open terminal use command Login

```
$ oc login --insecure-skip-tls-verify  --server=https://master00-XXXX.generic.opentlc.com:443
```

3.Create the OpenShift projects

```
$ export COOLSTORE_PRJ=simple-project

$ oc new-project ${COOLSTORE_PRJ}

$ oc project $COOLSTORE_PRJ

```

4.Deploy an instance of PostgresSQL

```
$ cd ~/lab/project-service

$ oc process -f src/ocp/freelancer-service-postgresql-persistent.yaml \
-p FREELANCER_DB_USERNAME=jboss \
-p FREELANCER_DB_PASSWORD=jboss \
-p FREELANCER_DB_NAME=freelancerdb | oc create -f - -n $COOLSTORE_PRJ
```

5.Deploy the Freelancer service application on OpenShift using the Fabric8 Maven plug-in

```
$ mvn clean fabric8:deploy -Popenshift -Dfabric8.namespace=$COOLSTORE_PRJ
```

6.Monitor the deployment of the project-service

```
$ oc get pods -n $COOLSTORE_PRJ -w
```

7.Test Service

```
$ export FREELANCER_URL=http://$(oc get route freelancers-service -n $COOLSTORE_PRJ -o template --template='{{.spec.host}}')

$ curl -X GET "$FREELANCER_URL/freelancers"
$ curl -X GET "$FREELANCER_URL/freelancers/{freelancerId}"

```