SUMMARY = "DoubleDecker an hierarchical distributed message system"
DESCRIPTION = "DoubleDecker is an hierarchical distributed message system based on ZeroMQ"
HOMEPAGE = "https://github.com/Acreo/DoubleDecker"
SECTION = "networking"

PR = "r1"

LICENSE = "GLGPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5sum=fc178bcd425090939a8b634d1d6a9594"

inherit setuptools

DEPENDS += ""
RDEPENDS += ""

SRC_URI = "git://github.com/Acreo/DoubleDecker-py.git"
SRCREV = "9ddee1b62dcf82ffcc16df72e12d59e736948e04"
