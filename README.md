## 镜像的cuda为11.6，和stable diffusion默认一样
## NVIDIA-SMI 510.54       Driver Version: 510.54       CUDA Version: 11.6

## 加载镜像 docker load < sdserver.docker.image.tar

## 用镜像启动一个container
```
docker run -u $(id -u):$(id -g) --net=host  --gpus='"device=0,"' --rm -it  -v ${PWD}:/home -p 7863:7863 -w /home lora bash
```

### 启动stable diffusion
```
ACCELERATE="True" REQS_FILE=requirements.txt CUDA_VISIBLE_DEVICES=$1 USE_MEMORY_EFFICIENT_ATTENTION=1 bash webui.sh -f \
    --no-hashing --skip-version-check --deepdanbooru --listen --enable-insecure-extension-access --disable-safe-unpickle --api  \
    --opt-split-attention \
    --xformers \
    --opt-channelslast \
    --port $2
# 注意替换显卡编号和port
# 浏览器能打开：localhost:7863
```


### 启动服务

```
直接在代码文件夹内 python3 tzserver.py  默认开出来的端口是5002， 使用的sd端口是7863
需要设置代理export http_proxy="***" && export https_proxy="***"
或者系统直接设置全局代理，1chatgpt2翻译 需要
```


### postman 请求的导入文件也在 tzserver压缩文件夹内


