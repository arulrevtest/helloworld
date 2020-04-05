#!/usr/bin/python

import yaml
import sys

repository_name = sys.argv[1]
new_version = sys.argv[2]

fname = repository_name + "values.yaml"

with open(fname) as f:
    dict_obj = yaml.load(f)

dict_obj["Image"]["version"] = new_version

with open(fname, "w") as f:
    yaml.dump(dict_obj, f)