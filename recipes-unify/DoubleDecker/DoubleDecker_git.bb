SUMMARY = "DoubleDecker an hierarchical distributed message system"
DESCRIPTION = "DoubleDecker is an hierarchical distributed message system based on ZeroMQ"
HOMEPAGE = "https://github.com/Acreo/DoubleDecker"
SECTION = "networking"

PR = "r1"

LICENSE = "GLGPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5sum=40d2542b8c43a3ec2b7f5da31a697b88"

inherit autotools

DEPENDS = "libsodium libzmq3 czmq"
RDEPENDS = ""

SRC_URI = "git://github.com/Acreo/DoubleDecker.git"
SRCREV = "00b702283cf1fc199056138280ea2ede04df37d0"

#do_configure()_prepend {
#  ./boot.sh
#}
