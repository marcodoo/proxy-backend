# Exercise 1

## How to create the Tekton pipeline

#### Name:
```
proxy-pipeline
```

#### Parameters:
| Name                | Description           | Default value                                      |
| :---                | :---                  | :---                                               |
| `github-repository` | GitHub URL Repository | `<copy the value from your github repository>`     |
| `registry`          | Container Registry    | `image-registry.openshift-image-registry.svc:5000` |
| `image-name`        | Proxy Image Name      | `proxy-backend`                                    |
| `kustomize-folder`  | Kustomize Folder      | `./k8s/base`                                       |

#### Workspaces:
```
source-code
```

#### Tasks:
1 - `git-clone`
| Property Name               | Property Value                   |                                 
| :---                        | :---                             |
| display name                | `git-clone`                      | 
| url                         | `$(params.github-repository)`    | 
| workspace (output)          | `source-code`                    |

2 - `maven`
| Property Name                       | Property Value        |                                
| :---                                | :---                  |
| display name                        | `maven`               | 
| goals                               | `package`             | 
| context_dir                         | `.`                   | 
| workspace (source)                  | `source-code`         |
| workspace (maven-settings)          | `source-code`         |

3 - `buildah`
| Property Name                       | Property Value                                                             |                                 
| :---                                | :---                                                                       |
| display name                        | `buildah`                                                                  | 
| image                               | `$(params.registry)/$(context.pipelineRun.namespace)/$(params.image-name)` | 
| dockerfile                          | `./Dockerfile`                                                             | 
| context                             | `.`                                                                        | 
| workspace (source)                  | `source-code`                                                              |

4 - `kustomize`
| Property Name                       | Property Value                                                             |                                                             
| :---                                | :---                                                                       |
| display name                        | `kustomize`                                                                | 
| base-folder-path                    | `$(params.kustomize-folder)`                                               | 
| image-with-tag                      | `$(params.registry)/$(context.pipelineRun.namespace)/$(params.image-name)` | 
| image-digest                        | `$(tasks.buildah.results.IMAGE_DIGEST)`                                    | 
| workspace (source)                  | `source-code`                                                              |

5 - `openshift-client`
| Property Name                   | Property Value                                                        |                               
| :---                            | :---                                                                  |
| display name                    | `openshift-client`                                                    | 
| script                          | `oc apply -f $(tasks.kustomize.results.manifests)`                    | 
| version                         | `latest`                                                              | 
| workspace (manifest-dir)        | `source-code`                                                         |

# Exercise 2

## How to deal with the mesh and deploy again your backend

 - Create a project called <name-surname>-mesh
 - Inside that project, create an instance of a service mesh
 - Add your project <name-surname> to the mesh
 - Create the gateway in your project

```
apiVersion: networking.istio.io/v1beta1
kind: Gateway
metadata:
  name: gw
  namespace: < your project >
spec:
  selector:
    istio: ingressgateway # use istio default controller
  servers:
  - port:
      number: 80
      name: http
      protocol: HTTP
    hosts:
    - "*"
  ```
   - Create the following resources in your project

  ```
  kind: VirtualService
apiVersion: networking.istio.io/v1alpha3
metadata:
  name: techgym-frontend-vsvc
  namespace: < your project >
spec:
  hosts:
    - >-
      < route associated to the previous gateway >
  gateways:
    - gw
  http:
    - route:
        - destination:
            host: techgym-frontend
            port:
              number: 8080
---
kind: VirtualService
apiVersion: networking.istio.io/v1alpha3
metadata:
  name: techgym-backend-vsvc
  namespace: < your project >
spec:
  hosts:
    - techgym-backend
  gateways:
    - gw
  http:
    - route:
        - destination:
            host: techgym-backend
            port:
              number: 8080
            subset: v2
          weight: 50
        - destination:
            host: techgym-backend
            port:
              number: 8080
            subset: v1
          weight: 50
---
apiVersion: networking.istio.io/v1beta1
kind: DestinationRule
metadata:
  name: techgym-backend-dr
  namespace: < your project >
spec:
  host: techgym-backend
  subsets:
    - labels:
        version: v2
      name: v2
    - labels:
        version: v1
      name: v1
 
  ```

- Make a commit to this [file](/k8s/base/kustomization.yaml), in order to update v1 to v2
