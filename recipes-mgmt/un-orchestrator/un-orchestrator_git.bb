SUMMARY = "Universal Node orchestrator (un-orchestrator)"
DESCRIPTION = "The Universal Node orchestrator (un-orchestrator) is the master of the universal node."
HOMEPAGE = "https://github.com/bisdn/un-orchestrator"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://../COPYRIGHT;md5=385b8aba0b3b88eaa7e5377eefa10f94"
SECTION = "console/tools"

SRC_URI = "git://github.com/netgroup-polito/un-orchestrator.git;branch=new_master \
        file://fixcmake.patch \
        file://fix_ISO_C90_warning.patch"
SRCREV = "409d9a4259404aeb149570fb176912664ddd4139"

DEPENDS = "boost json-spirit libmicrohttpd libvirt openvswitch rofl-common libxml2 ethtool openssl sqlite3"
RDEPENDS_${PN} = "rofl-common json-spirit libsqlite3 openvswitch"

S = "${WORKDIR}/git/orchestrator"

EXTRA_OECMAKE=""
inherit pkgconfig cmake

do_configure_prepend() {
       cd ${S}/../contrib
       unzip -o inih.zip
       cd inih
       cp * ../../orchestrator/node_resource_manager/database_manager/SQLite
}

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${sysconfdir}/default/node-orchestrator
        install -m 0755 ${WORKDIR}/build/node-orchestrator ${D}${bindir}/node-orchestrator
        install -m 0644 ${S}/config/* ${D}${sysconfdir}/default/node-orchestrator
}
