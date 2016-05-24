# meta-unify

This is a yocto layer which holds recipes for the UNIFY project:
https://www.fp7-unify.eu/

It is supposed to be added onto a CarOS build-host,
which usually means you have the following additional
layers: poky, meta-caros, meta-virtualization and some layers 
of meta-openembedded.

Also see https://github.com/carosio/caros-release

# How To Build

To build a deployable image use the [redomat](https://github.com/carosio/redomat),
please follow the install instructions provided in its README.

The redomat is a simple tool to build an yocto image using a docker container.
To do so execute:

```
redomat.py releases/unify-caros-<version>-lamobo-r1.xml
```

This will build a ready to use SD card image for the bananapi.

# How To Serve Build Artifacts

The result of a build is what we call build artifacts. Build artifacts can
be build logs, file system images, packages and more. It is possible to serve
these build artifacts via the Docker container used for building:

```
docker run --rm -ti -p <hostport>:80 <image> /REDO/results/serve.sh
```

This will make the build artifacts accessible on `http://localhost:<hostport>`

# License

All meta data is MIT licensed unless otherwise stated. Source code included
in tree for individual recipes is under the LICENSE stated in each recipe
(.bb file) unless otherwise stated.

