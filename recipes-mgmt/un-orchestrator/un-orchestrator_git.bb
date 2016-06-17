SUMMARY = "Universal Node orchestrator (un-orchestrator)"
DESCRIPTION = "The Universal Node orchestrator (un-orchestrator) is the master of the universal node."
HOMEPAGE = "https://github.com/bisdn/un-orchestrator"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=385b8aba0b3b88eaa7e5377eefa10f94"
SECTION = "console/tools"

PR = "r2"

SRC_URI = "git://github.com/wfailla/un-orchestrator.git;branch=build-improvements \
        file://0001-use-cross-compile-capable-inc-dirs.patch \
        file://fix_ISO_C90_warning.patch"
SRCREV = "06ebdfa4243085a3bfbdafc9f01e46236f3a2a78"

DEPENDS = "boost json-spirit libmicrohttpd libvirt openvswitch rofl-common libxml2 ethtool openssl sqlite3"
RDEPENDS_${PN} = "rofl-common json-spirit libsqlite3 openvswitch"

S = "${WORKDIR}/git"

EXTRA_OECMAKE="-DLOGGING_LEVEL=ORCH_DEBUG_INFO -DBUILD_ExternalProjects=OFF"
inherit pkgconfig cmake

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${sysconfdir}/default/node-orchestrator
        install -m 0755 ${WORKDIR}/build/orchestrator/node-orchestrator ${D}${bindir}/node-orchestrator
        install -m 0644 ${S}/orchestrator/config/* ${D}${sysconfdir}/default/node-orchestrator
}
