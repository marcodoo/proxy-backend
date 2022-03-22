# How to create the Tekton pipeline

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
