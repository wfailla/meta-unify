DESCRIPTION = "Hostinfo application for Unify"
SECTION = "network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "844804e153212dfcf26c366545f52c6129808a4d"

SRC_URI = "git://github.com/carosio/unify-hostinfo;protocol=https"
PR="r1"

DEPENDS += "erlang-isis"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("unify-hostinfo", "unify_hostinfo-*", "ebin", "src", d)
}
