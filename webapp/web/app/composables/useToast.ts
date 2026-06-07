import { useToast as useLibToast } from 'vue-toastification';
import { TEXT } from '~/constants/text';
import { STATES_CODE } from '~/constants/api-status';
import type { CustomAxiosError } from '~/types/customAxiosError';

export const useToast = () => {
  const toast = useLibToast();
  const showSuccessToast = (message: string) => {
    toast.success(message, {
      timeout: false,
      closeOnClick: true,
      bodyClassName: 'pre-line-toast',
    });
  };

  const showApiErrorToast = (error: CustomAxiosError) => {
    const statusCode = error?.response?.status;
    const errorMessage = error?.response?.data?.message?.[0];

    let defaultMessage: string = TEXT.ERROR.UNKNOWNERROR;

    switch (statusCode) {
      case STATES_CODE.BAD_REQUEST:
        defaultMessage = TEXT.ERROR.ERROR400;
        break;
      case STATES_CODE.UNAUTHORIZED:
        defaultMessage = TEXT.ERROR.ERROR401;
        break;
      case STATES_CODE.FORBIDDEN:
        defaultMessage = TEXT.ERROR.ERROR403;
        break;
      case STATES_CODE.NOT_FOUND:
        defaultMessage = TEXT.ERROR.ERROR404;
        break;
      case STATES_CODE.CONFLICT:
        defaultMessage = TEXT.ERROR.ERROR409;
        break;
      case STATES_CODE.INTERNAL_SERVER_ERROR:
        defaultMessage = TEXT.ERROR.ERROR500;
        break;
      default:
        defaultMessage = TEXT.ERROR.UNKNOWNERROR;
    }
    const finalMessage = errorMessage || defaultMessage;
    toast.error(finalMessage, {
      timeout: false,
      closeOnClick: true,
      bodyClassName: 'pre-line-toast',
    });
  };

  return { showSuccessToast, showApiErrorToast };
};
