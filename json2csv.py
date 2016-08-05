import json
import sys

json_file, platform = sys.argv[1:]

with open(json_file, 'r') as f_in:
  data = json.loads(f_in.read())

for bench in data:
  name = bench['benchmark']
  name = name.replace('net.greypanther.opbench.primitives.Benchmark', '').lower()
  type_name, op_name = name.split('.')

  percentiles = bench['primaryMetric']['scorePercentiles']
  kind = bench.get('params', dict(kind='N/A'))['kind']

  if kind == 'N/A':
    nice_name = '%s.%s' % (type_name, op_name)
  else:
    nice_name = '%s.%s.%s' % (type_name, op_name, kind.lower())

  print '\t'.join(str(e) for e in (
    platform, type_name, op_name, kind, nice_name, percentiles['0.0'],
    percentiles['50.0'], percentiles['99.999'], percentiles['100.0']
  ))

