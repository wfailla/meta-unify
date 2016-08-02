SUMMARY = "ZeroMQ lightweight messaging kernel"
DESCRIPTION = "The ZeroMQ lightweight messaging kernel is a library which extends the standard socket interfaces with features traditionally provided by specialised messaging middleware products."
SECTION = "networking"

PR = "r1"

LICENSE = "LGPL"
LIC_FILES_CHKSUM = "file://README.md;md5sum=12f2ba74c708b07e887b82e404f7f885"

inherit autotools

S ="${WORKDIR}/git"

SRC_URI = "git://github.com/zeromq/libzmq.git"
SRCREV = "5e684172d654cb8d7e8e7ec703e13e96c9536453"

do_configure_prepend() {
        ${S}/autogen.sh
}
