SUMMARY = "Universal Node orchestrator (un-orchestrator)"
DESCRIPTION = "The Universal Node orchestrator (un-orchestrator) is the master of the universal node."
HOMEPAGE = "https://github.com/bisdn/un-orchestrator"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=385b8aba0b3b88eaa7e5377eefa10f94"
SECTION = "console/tools"

PR = "r3"

inherit systemd

SYSTEMD_SERVICE_${PN} = "uno.service"

SRC_URI = "git://github.com/thz/un-orchestrator.git;branch=build-improvements \
        file://0001-use-cross-compile-capable-inc-dirs.patch \
        file://uno.service \
        file://uno-name-resolver.service \
        file://EnvironmentFile \
        file://prestart.sh "
SRCREV = "7a62382315b4d762578549ef9630417325e6d794"

DEPENDS = "boost json-spirit libmicrohttpd libvirt openvswitch rofl-common libxml2 ethtool openssl sqlite3"
RDEPENDS_${PN} = "rofl-common json-spirit libsqlite3 openvswitch"

S = "${WORKDIR}/git"

EXTRA_OECMAKE="-DLOGGING_LEVEL=ORCH_DEBUG_INFO -DBUILD_ExternalProjects=OFF"
inherit pkgconfig cmake

nameresolver_configs += "/etc/uno-name-resolver/config/bng.xml"
nameresolver_configs += "/etc/uno-name-resolver/config/example.xml"
nameresolver_configs += "/etc/uno-name-resolver/config/network-functions.xsd"

CONFFILES_${PN} += "$nameresolver_configs"

FILES_${PN} += "${sysconfdir}/uno/prestart.sh \
    ${sysconfdir}/uno/env \
    ${systemd_unitdir}/system/uno.service \
    ${systemd_unitdir}/system/uno-name-resolver.service \
    ${bindir}/node-orchestrator \
    ${sysconfdir}/default/node-orchestrator"

do_install() {
        install -d ${D}${bindir}
        install -d ${D}${sysconfdir}/default/node-orchestrator
        install -m 0755 ${WORKDIR}/build/orchestrator/node-orchestrator ${D}${bindir}/node-orchestrator
        install -m 0644 ${S}/orchestrator/config/* ${D}${sysconfdir}/default/node-orchestrator

        install -m 0755 ${WORKDIR}/build/name-resolver/name-resolver ${D}${bindir}/name-resolver

        install -d ${D}${sysconfdir}/uno
        install -m 0755 ${WORKDIR}/prestart.sh ${D}${sysconfdir}/uno/prestart.sh
        install -m 0644 ${WORKDIR}/EnvironmentFile ${D}${sysconfdir}/uno/env

        install -d ${D}${systemd_unitdir}/system
        install -m 0644 ${WORKDIR}/uno.service ${D}${systemd_unitdir}/system/uno.service

        # uno-name-resolver config
        install -m 0644 ${WORKDIR}/uno.service ${D}${systemd_unitdir}/system/uno-name-resolver.service
        install -d ${D}${sysconfigdir}/uno-name-resolver
        install -d ${D}${sysconfigdir}/uno-name-resolver/config

        ln -snf . ${S}/etc ; ln -snf . ${S}/uno-name-resolver
        for x in ${nameresolver_configs} ; do
          install -m 0644 -o 0 -g 0 ${S}/$x ${D}$x
        done
        rm ${S}/etc ${S}/uno-name-resolver
}
