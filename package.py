#!/usr/bin/python
#coding=utf-8

# This script will create a package for AnyStore SDK
import os
import traceback
import shutil, errno
import zipfile

def zip(src, dst):
    zf = zipfile.ZipFile("%s.zip" % (dst), "w")
    abs_src = os.path.abspath(src)
    for dirname, subdirs, files in os.walk(src):
        for filename in files:
            absname = os.path.abspath(os.path.join(dirname, filename))
            arcname = absname[len(abs_src) + 1:]
            # print 'zipping %s as %s' % (os.path.join(dirname, filename), arcname)
            zf.write(absname, arcname)
    zf.close()

def copyanything(src, dst):
    try:
        shutil.copytree(src, dst)
    except OSError as exc: # python >2.5
        if exc.errno == errno.ENOTDIR:
            shutil.copy(src, dst)
        else: raise

def main():
	from optparse import OptionParser
	parser = OptionParser(usage='usage: %prog -v 1.0')
	parser.add_option('-v', '--version',dest='version', help="Version number for the SDK")
	opts, args = parser.parse_args()
	if not opts.version:
		parser.error('please specify version with -v')

	if opts.version:
		version = opts.version


	pubDir = '/publish'
	binDir = '/publish/bin'
	demoDir = '/Demo'
	sdkDir = '/SDK'
	archiveName = '/AnyStore_SDK'

	print('==> Clean up git repo')
	from subprocess import call
	call("git clean -dxf", shell=True)
	print

	print('==> creating publish folder')
	currDir = os.getcwd()
	publishPath = currDir + pubDir
	binPath = currDir + binDir

	docPath = currDir + '/docs/guide.md'
	outDocPath = binPath + '/Readme.html'

	print(publishPath)
	if os.path.exists(publishPath):
		shutil.rmtree(publishPath)
	os.makedirs(publishPath)
	os.makedirs(binPath)
	print

	print('==> generating documentation form markdown')
	from grip import export
	export(path=docPath, out_filename=outDocPath)
	print

	print('==> copy resources to publish folder')
	copyanything(currDir + sdkDir, binPath + sdkDir)
	copyanything(currDir + demoDir, binPath + demoDir)
	print

	print('==> create archive')
	zip(binPath, publishPath + archiveName + version)
	print

# ---------- main -------------
if __name__ == '__main__':
    try:
        main()
    except Exception as e:
        traceback.print_exc()
        sys.exit(1)