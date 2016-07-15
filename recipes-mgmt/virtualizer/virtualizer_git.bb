SUMMARY="Intermediate module sitting between un-orchestrator and upper layers of the Unify architecture."
DESCRIPTION="The Virtualizer is an intermediate module sit between the un-orchestrator and the upper layers of the Unify architecture."
LICENSE="CLOSED"

PR="r2"

SRC_URI = "http://nas01/wfailla/unify/virtualizer.tar"
SRC_URI += "file://virtualizer.sh"

SRC_URI[md5sum] = "4a05571e92baa329991e5ade4a956978"
SRC_URI[sha256sum] = "b0e05c1d527c02b76f071cc56173835d593f9bdf43de6068407fd53ca2304187"

S = "${WORKDIR}/virtualizer"

# SRC_URI += "file://unify-virtualizer.service"
# inherit caros-service
# SYSTEMD_SERVICE_${PN} += "unify-virtualizer.service"

DEPENDS="python-pip"
RDEPENDS_${PN} = "un-orchestrator gunicorn falcon python-cython python-requests"

virtualizer_configs += "/opt/virtualizer/config/universal-node-schema.xsd"
virtualizer_configs += "/opt/virtualizer/config/universal-node-example.xml"
virtualizer_configs += "/opt/virtualizer/config/nffg_examples/simple_passthrough_nffg.xml"
virtualizer_configs += "/opt/virtualizer/config/nffg_examples/nffg_delete_flow_vnf.xml"
virtualizer_configs += "/opt/virtualizer/config/nffg_examples/passthrough_with_vnf_nffg_v5.xml"
virtualizer_configs += "/opt/virtualizer/config/nffg_examples/delete_passthrough_with_vnf_nffg_v5.xml"
virtualizer_configs += "/opt/virtualizer/config/nffg_examples/passthrough_with_vnf_nffg.xml"
virtualizer_configs += "/opt/virtualizer/config/nffg_examples/passthrough_with_vnf_nffg_and_match_and_action.xml"
virtualizer_configs += "/opt/virtualizer/config/configuration.ini"

do_install() {
	install -m 0755 -d ${D}/opt/virtualizer/virtualizer_library/
	install -m 0755 -d ${D}/opt/virtualizer/un_native_nffg_library/
	install -m 0644 -o 0 -g 0 ${S}/*.py ${D}/opt/virtualizer/
	install -m 0644 -o 0 -g 0 ${S}/virtualizer_library/*.py ${D}/opt/virtualizer/virtualizer_library/
	install -m 0644 -o 0 -g 0 ${S}/un_native_nffg_library/*.py ${D}/opt/virtualizer/un_native_nffg_library/

	# config
	install -m 0755 -d ${D}/opt/virtualizer/config
	install -m 0755 -d ${D}/opt/virtualizer/config/nffg_examples
	ln -snf . ${S}/opt ; ln -snf . ${S}/virtualizer
	for x in ${virtualizer_configs} ; do
	  install -m 0644 -o 0 -g 0 ${S}/$x ${D}$x
	done
	rm ${S}/opt ${S}/virtualizer

	install -m 0755 -d ${D}/${bindir}
	install -m 0755 -o 0 -g 0 ${WORKDIR}/virtualizer.sh ${D}/${bindir}/virtualizer
	
	# install -d ${D}${systemd_unitdir}/system
	# install -m 0644 ${WORKDIR}/unify-virtualizer.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} = "/opt/virtualizer ${bindir}/virtualizer"
CONFFILES_${PN} += "$virtualizer_configs"

# RUN V:
# gunicorn -b ip:port virtualizer:api
# where 'ip' and 'port' must be set to the desired values.
# Please, note that the Virtualizer requires the un-orchestrator and the
# name-resolver running in the server.
#
# TODO:
# * service file
# * recipies for dependencies maybe install all unsing python-pip
#   * cython
#     * http://cython.org/#documentation
#     * http://docs.cython.org/src/quickstart/install.html
