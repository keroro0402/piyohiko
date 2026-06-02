import type { AxiosError } from 'axios';
import { STATES_CODE } from '~/constants/api-status';
import { TEXT } from '~/constants/text';

const errorHandler = (error: unknown): string => {
  if (!(error instanceof Error)) return TEXT.ERROR.UNKNOWNERROR;

  const axiosError = error as AxiosError;
  const status = axiosError.response?.status || STATES_CODE.INTERNAL_SERVER_ERROR;
  switch (status) {
    case STATES_CODE.UNAUTHORIZED:
      return TEXT.ERROR.ERROR401;
    case STATES_CODE.FORBIDDEN:
      return TEXT.ERROR.ERROR403;
    case STATES_CODE.CONFLICT:
      return TEXT.ERROR.ERROR409;
    case STATES_CODE.INTERNAL_SERVER_ERROR:
      return TEXT.ERROR.ERROR500;
    case STATES_CODE.BAD_REQUEST:
      return TEXT.ERROR.ERROR400;
    default:
      return TEXT.ERROR.UNKNOWNERROR;
  }
};

export { errorHandler };
