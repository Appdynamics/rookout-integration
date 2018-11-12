import tornado.ioloop, tornado.web
import argparse,configparser
import sys, getopt
import json
import requests

class SnapshotRequester(object) :
    def __int__(self,controllerEndpoint=None,applicationName=None):
        if not controllerEndpoint: raise AssertionError

    def getSnapshot(self,guuid):
        headerDict = {"":""}
        url = self.controllerEndpoint +"/controller/rest/" + self.applicationName + "/request-snapshots"
        parms = {"guids":guuid," output":"JSON", "need-exit-calls":"true","range-type":""}
        requests.get(url,params=parms)



class RookIntegration(object) :
    config = configparser.ConfigParser()

    def __init__(self, command_args=None):
        if command_args:
            self.command_args = command_args
        else:
            parser = argparse.ArgumentParser()
            parser.add_argument("--configFile", "-c", required=True, default="config.properties",
                                help="config file is needed")

        self.command_args = parser.parse_args()
        self.config.read(self.command_args.configFile)


class MainHandler(tornado.web.RequestHandler):
    json_data = None

    def prepare(self):
        super(MainHandler, self).prepare()
        self.json_data = None
        if self.request.body:
            try:
                self.json_data = tornado.escape.json_decode(self.request.body)
            except ValueError:
                print("error")

    def get_argument(self, arg, default=None):
        # TODO: there's more arguments in the default get_argument() call
        # TODO: handle other method types
        if self.request.method in ['POST', 'PUT'] and self.json_data:
            return self.json_data.get(arg, default)
        else:
            return super(MainHandler, self).get_argument(arg, default)


    def get(self):
        self.write(self.get_argument("details")['GUID'])

    def post(self):
        print("guid is " + self.get_argument("details")['GUID'])
        self.write(self.get_argument("details")['GUID'])


def make_app():
    return  tornado.web.Application([(r"/", MainHandler),])

if __name__ == "__main__":
    integration = RookIntegration()
    ws_port = integration.config.get("ports","WebServerPort")

    app = make_app()
    app.listen(int(ws_port))
    tornado.ioloop.IOLoop.current().start()