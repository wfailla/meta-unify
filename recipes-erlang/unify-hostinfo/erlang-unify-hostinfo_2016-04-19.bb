SUMMARY = "Hostinfo application for Unify"
SECTION = "network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "c912fdfe41b803eb40731e3e7be88c75869421b8"

SRC_URI = "git://github.com/carosio/unify-hostinfo;protocol=https"
PR="r1"

DEPENDS += "erlang-isis"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("unify-hostinfo", "unify_hostinfo-*", "ebin", "src", d)
}
