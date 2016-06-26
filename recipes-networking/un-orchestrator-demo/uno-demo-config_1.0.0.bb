SUMMARY = "Universal Node orchestrator demo"
DESCRIPTION = "The Universal Node orchestrator demo configuration pass through"
HOMEPAGE = "https://github.com/bisdn/un-orchestrator"
LICENSE = "other"

PR = "r1"

SRC_URI = "file://EnvironmentFile \
        file://uno-config.ini "

RDEPENDS_${PN} = "un-orchestrator"

FILES_${PN} += "${sysconfdir}/uno/prestart.sh \ "

do_install() {
        install -d ${D}${sysconfdir}/default/node-orchestrator
        install -m 0644 ${WORKDIR}/uno-config.ini ${D}${sysconfdir}/default/node-orchestrator/demo-config.ini

        install -d ${D}${sysconfdir}/uno
        install -m 0644 ${WORKDIR}/EnvironmentFile ${D}${sysconfdir}/uno/env
}
