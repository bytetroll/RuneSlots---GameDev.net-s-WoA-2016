use strict;
use warnings;
use diagnostics;

use File::Copy::Recursive qw(dircopy rcopy);
use File::Path qw(make_path);

use File::Spec::Functions 'catfile';

#my $srcDir = "./pack/";
#my $destDir = "./build/woa2016/";

my $srcDir = $ARGV[0];
my $destDir = $ARGV[1];

#my $heartbeat = 5;

#while(1) {
	print("\n\nBytetroll File Packer\n");
	for(my $i = 0; $i < 60; $i++) {
		print("-");
	}
	print("\n");
	print("PACK BEGIN\n");
	opendir(DIR, $srcDir) or die "\tError opening $srcDir\n";

	foreach my $filename(readdir(DIR)) {
		if(not -e $destDir . $filename) {
			my $srcPath = catfile($srcDir, $filename);
			my $destPath = catfile($destDir, $filename);

			if($srcPath eq "." or $srcPath eq "..") {
				continue;
			}

			print("\tPacking $filename to $destDir\n");
			dircopy($srcPath, $destPath);
			rcopy($srcPath, $destPath);
		} else {
			my $isDir = grep {-d} $filename;

			if(!$isDir) {
				print("\t$filename already packed... skipping\n");
			}
		}
	}
	print("PACK END\n\n");

	closedir(DIR);
	#sleep($heartbeat);
#}