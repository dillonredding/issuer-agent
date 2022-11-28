# WebVC Issuer Agent

Example implementation of an issuer agent issuing verifiable credentials over the web.

## Development

```shell
# generate ED25519 private key key
$ openssl genpkey -algorithm ed25519 -out private.pem

# generate public key from private key
$ openssl pkey -in private.pem -pubout -out public.pem
```

On macOS, you may need to use [openssl@1.1](https://formulae.brew.sh/formula/openssl@1.1) in order to use the `ed25519` algorithm.
