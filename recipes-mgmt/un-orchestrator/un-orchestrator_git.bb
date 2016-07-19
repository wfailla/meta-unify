SUMMARY = "Universal Node orchestrator (un-orchestrator)"
DESCRIPTION = "The Universal Node orchestrator (un-orchestrator) is the master of the universal node."
HOMEPAGE = "https://github.com/bisdn/un-orchestrator"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=385b8aba0b3b88eaa7e5377eefa10f94"
SECTION = "console/tools"

PR = "r7"

inherit systemd

SYSTEMD_SERVICE_${PN} = "uno.service"

SRC_URI = "git://github.com/thz/un-orchestrator.git;branch=buildsystem-improvements \
        file://0001-use-cross-compile-capable-inc-dirs.patch \
        file://uno.service \
        file://uno-name-resolver.service \
        file://prestart.sh "
SRCREV = "24b344d9f43e25cba4fccf55252e79cc4bbd3901"

DEPENDS = "boost json-spirit libmicrohttpd libvirt openvswitch rofl-common libxml2 ethtool openssl sqlite3"
RDEPENDS_${PN} = "rofl-common json-spirit libsqlite3 openvswitch"

S = "${WORKDIR}/git"

EXTRA_OECMAKE="-DENABLE_NATIVE=ON -DENABLE_DOCKER=ON -DLOGGING_LEVEL=ORCH_DEBUG_INFO -DBUILD_ExternalProjects=OFF"
inherit pkgconfig cmake

CONFFILES_${PN} += "/etc/uno-name-resolver/config/bng.xml"
CONFFILES_${PN} += "/etc/uno-name-resolver/config/example.xml"
CONFFILES_${PN} += "/etc/uno-name-resolver/config/network-functions.xsd"

FILES_${PN} += "${sysconfdir}/uno/prestart.sh \
    ${sysconfdir}/uno/env \
    ${systemd_unitdir}/system/uno.service \
    ${systemd_unitdir}/system/uno-name-resolver.service \
    ${bindir}/node-orchestrator \
    ${sysconfdir}/default/node-orchestrator"

do_install() {
        install -d ${D}${bindir}
        install -m 0755 ${WORKDIR}/build/orchestrator/node-orchestrator ${D}${bindir}/node-orchestrator

        install -d ${D}${sysconfdir}/default/node-orchestrator
        install -m 0644 ${S}/orchestrator/config/* ${D}${sysconfdir}/default/node-orchestrator

        install -m 0755 ${WORKDIR}/build/name-resolver/name-resolver ${D}${bindir}/name-resolver

        install -d ${D}${sysconfdir}/uno
        install -m 0755 ${WORKDIR}/prestart.sh ${D}${sysconfdir}/uno/prestart.sh

        # systemd units
        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/uno.service ${D}${systemd_unitdir}/system/uno.service
        install -m 0644 ${WORKDIR}/uno-name-resolver.service ${D}${systemd_unitdir}/system/uno-name-resolver.service

        # uno-name-resolver config
        install -d ${D}${sysconfdir}/uno-name-resolver/config
        install -m 0644 -o 0 -g 0 ${S}/name-resolver/config/bng.xml ${D}${sysconfdir}/uno-name-resolver/config/
        install -m 0644 -o 0 -g 0 ${S}/name-resolver/config/example.xml ${D}${sysconfdir}/uno-name-resolver/config/
        install -m 0644 -o 0 -g 0 ${S}/name-resolver/config/network-functions.xsd ${D}${sysconfdir}/uno-name-resolver/config/

        # plugins
        install -d ${D}${sysconfdir}/uno/compute_controller/plugins/native/
        install -m 0644 -o 0 -g 0 \
            ${S}/orchestrator/compute_controller/plugins/native/Capabilities.xsd \
            ${S}/orchestrator/compute_controller/plugins/native/Capabilities.xml \
            ${D}${sysconfdir}/uno/compute_controller/plugins/native/
        ln -snf . ${D}${sysconfdir}/uno/orchestrator

}
